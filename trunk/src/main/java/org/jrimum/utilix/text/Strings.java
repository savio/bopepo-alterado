/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:18:19
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:18:19
 * 
 */

package org.jrimum.utilix.text;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;
import static org.apache.commons.lang.StringUtils.removeStart;
import static org.apache.commons.lang.StringUtils.replace;
import static org.apache.commons.lang.StringUtils.replaceChars;
import static org.apache.commons.lang.StringUtils.startsWith;
import static org.jrimum.utilix.Objects.isNotNull;

import org.jrimum.utilix.Objects;

/**
 * <p>
 * Esta classe tem a responsabilidade de prover serviços utilitários
 * relacionados a manipulação de <code>Strings</code>
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public final class Strings {

	public static final String WHITE_SPACE = " ";

	/**
	 * Utility class pattern: classe não instanciável
	 * 
	 * @throws AssertionError
	 *             caso haja alguma tentativa de utilização deste construtor.
	 */
	private Strings() {

		throw new AssertionError("NOT SUPORTED OPERATION!");
	}

	/**
	 * <p>
	 * Elimina simbolos como:
	 * 
	 * <pre>>
	 * <,;.:!*&%+-_<>[]\/
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            String com os símbolos a serem removidos.
	 * @return String sem símbolos.
	 * @since 0.2
	 */
	public static String eliminateSymbols(final String str) {

		String modifiedStr = str;

		if (isNotBlank(modifiedStr)) {

			modifiedStr = replace(modifiedStr, "-", EMPTY);
			modifiedStr = replace(modifiedStr, "_", EMPTY);
			modifiedStr = replace(modifiedStr, "=", EMPTY);
			modifiedStr = replace(modifiedStr, "+", EMPTY);
			modifiedStr = replace(modifiedStr, "%", EMPTY);
			modifiedStr = replace(modifiedStr, "*", EMPTY);
			modifiedStr = replace(modifiedStr, "@", EMPTY);
			modifiedStr = replace(modifiedStr, "#", EMPTY);
			modifiedStr = replace(modifiedStr, "&", EMPTY);
			modifiedStr = replace(modifiedStr, ":", EMPTY);
			modifiedStr = replace(modifiedStr, ".", EMPTY);
			modifiedStr = replace(modifiedStr, ";", EMPTY);
			modifiedStr = replace(modifiedStr, ",", EMPTY);
			modifiedStr = replace(modifiedStr, "!", EMPTY);
			modifiedStr = replace(modifiedStr, "?", EMPTY);
			modifiedStr = replace(modifiedStr, "(", EMPTY);
			modifiedStr = replace(modifiedStr, ")", EMPTY);
			modifiedStr = replace(modifiedStr, "{", EMPTY);
			modifiedStr = replace(modifiedStr, "}", EMPTY);
			modifiedStr = replace(modifiedStr, "[", EMPTY);
			modifiedStr = replace(modifiedStr, "]", EMPTY);
			modifiedStr = replace(modifiedStr, "/", EMPTY);
			modifiedStr = replace(modifiedStr, "\\", EMPTY);
			modifiedStr = replace(modifiedStr, ">", EMPTY);
			modifiedStr = replace(modifiedStr, "<", EMPTY);
			modifiedStr = replace(modifiedStr, "\"", EMPTY);
			modifiedStr = replace(modifiedStr, "'", EMPTY);
			modifiedStr = replace(modifiedStr, "`", EMPTY);
		}

		return modifiedStr;
	}

	/**
	 * <p>
	 * Remove os zeros iniciais de uma <code>String</code>, seja ela numérica ou
	 * não.
	 * </p>
	 * <p>
	 * <code>removeStartWithZeros("00000") => 0</code><br />
	 * <code>removeStartWithZeros("00023") => 23</code><br />
	 * <code>removeStartWithZeros("02003") => 2003</code>
	 * <p>
	 * 
	 * @param str
	 * @return a string sem zeros inicias ou um único zero.
	 * 
	 * @since 0.2
	 */

	public static String removeStartWithZeros(final String str) {

		String withoutZeros = EMPTY;
		final String zero = "0";

		if (isNotNull(str)) {

			if (startsWith(str, zero)) {

				withoutZeros = removeStart(str, zero);

				while (startsWith(withoutZeros, zero)) {
					withoutZeros = removeStart(withoutZeros, zero);
				}

				if (withoutZeros.trim().length() == 0) {
					withoutZeros = zero;
				}

			} else {
				withoutZeros = str;
			}
		}

		return withoutZeros;
	}

	/**
	 * <p>
	 * Remove a acentuação do texto, que inclui os acentos:
	 * <ul>
	 * <li>Agudo. ex.: á</li>
	 * <li>Grave. ex.: à</li>
	 * <li>Til. ex.: ã</li>
	 * <li>Trema. ex.: ä</li>
	 * <li>Circunflexo. ex.: â</li>
	 * </ul>
	 * e o Cedilha (ç).
	 * </p>
	 * <p>
	 * Os acentos são removidos tanto para letras minúsculas como para letras
	 * maiúsculas.
	 * </p>
	 * 
	 * @param value
	 *            String com os caracteres a serem removidos.
	 * @return String sem acentuação.
	 * @since 0.2
	 */
	public static String eliminateAccent(final String value) {

		String modifiedValue = value;

		// Para ç e Ç
		modifiedValue = replaceChars(modifiedValue, '\u00E7', 'c');
		modifiedValue = replaceChars(modifiedValue, '\u00C7', 'C');

		// Para à, á, â, ã e ä
		modifiedValue = replaceChars(modifiedValue, '\u00E0', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E1', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E2', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E3', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E4', 'a');

		// Para è, é, ê e ë
		modifiedValue = replaceChars(modifiedValue, '\u00E8', 'e');
		modifiedValue = replaceChars(modifiedValue, '\u00E9', 'e');
		modifiedValue = replaceChars(modifiedValue, '\u00EA', 'e');
		modifiedValue = replaceChars(modifiedValue, '\u00EB', 'e');

		// Para ì, í, î e ï
		modifiedValue = replaceChars(modifiedValue, '\u00EC', 'i');
		modifiedValue = replaceChars(modifiedValue, '\u00ED', 'i');
		modifiedValue = replaceChars(modifiedValue, '\u00EE', 'i');
		modifiedValue = replaceChars(modifiedValue, '\u00EF', 'i');

		// Para ò, ó, ô, õ e ö
		modifiedValue = replaceChars(modifiedValue, '\u00F2', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F3', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F4', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F5', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F6', 'o');

		// Para ù, ú, û e ü
		modifiedValue = replaceChars(modifiedValue, '\u00F9', 'u');
		modifiedValue = replaceChars(modifiedValue, '\u00FA', 'u');
		modifiedValue = replaceChars(modifiedValue, '\u00FB', 'u');
		modifiedValue = replaceChars(modifiedValue, '\u00FC', 'u');

		// Para À, Á, Â, Ã e Ä
		modifiedValue = replaceChars(modifiedValue, '\u00C0', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C1', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C2', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C3', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C4', 'A');

		// Para È, É, Ê e Ë
		modifiedValue = replaceChars(modifiedValue, '\u00C8', 'E');
		modifiedValue = replaceChars(modifiedValue, '\u00C9', 'E');
		modifiedValue = replaceChars(modifiedValue, '\u00CA', 'E');
		modifiedValue = replaceChars(modifiedValue, '\u00CB', 'E');

		// Para Ì, Í, Î e Ï
		modifiedValue = replaceChars(modifiedValue, '\u00CC', 'I');
		modifiedValue = replaceChars(modifiedValue, '\u00CD', 'I');
		modifiedValue = replaceChars(modifiedValue, '\u00CE', 'I');
		modifiedValue = replaceChars(modifiedValue, '\u00CF', 'I');

		// Para Ò, Ó, Ô, Õ e Ö
		modifiedValue = replaceChars(modifiedValue, '\u00D2', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D3', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D4', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D5', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D6', 'O');

		// Para Ù, Ú, Û e Ü
		modifiedValue = replaceChars(modifiedValue, '\u00D9', 'U');
		modifiedValue = replaceChars(modifiedValue, '\u00DA', 'U');
		modifiedValue = replaceChars(modifiedValue, '\u00DB', 'U');
		modifiedValue = replaceChars(modifiedValue, '\u00DC', 'U');

		return modifiedValue;
	}

	/**
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code> e não é numérica, ou seja, se a string não contém
	 * somente dígitos unicode.
	 * </p>
	 * <p>
	 * Lança exceção, com a mensagem passada por parâmetro (segundo parâmetro
	 * String), caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param message
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @thows IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string não seja numérica.
	 * 
	 * @since 0.2
	 */
	public static void checkNotNumeric(String value, String message) {

		checkNotNumeric(value, message, message);
	}

	/**
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code> e não é numérica, ou seja, se a string não contém
	 * somente dígitos unicode.
	 * </p>
	 * <p>
	 * Lança exceção, com a mensagem passada por parâmetro (segundo parâmetro
	 * String), caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @thows IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string não seja numérica.
	 * 
	 * @since 0.2
	 */
	public static void checkNotNumeric(String value) {

		checkNotNumeric(value, "String nula!", format(
				"Valor inválido. String [\"%s\"] não numérica!", value));
	}

	/**
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code>, não é vazia (<code>EMPTY</code>) e não possui apenas
	 * espaços em branco.
	 * </p>
	 * <p>
	 * Lança exceção, com a mensagem passada por parâmetro (segundo parâmetro
	 * String), caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param message
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @thows IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string seja vazia.
	 * 
	 * @since 0.2
	 */
	public static void checkNotBlank(String value, String message) {

		checkNotBlank(value, message, message);
	}

	/**
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code>, não é vazia (<code>EMPTY</code>) e não possui apenas
	 * espaços em branco. Lança exceção caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @thows IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string seja vazia.
	 * 
	 * @since 0.2
	 */
	public static void checkNotBlank(String value) {

		checkNotBlank(
				value,
				"String nula!",
				format(
						"Valor inválido. String [\"%s\"] vazia ou contendo somente espaços em branco!",
						value));
	}

	/**
	 * <p>
	 * Método privado para fins de reutilização de código.
	 * </p>
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code> e não é numérica, ou seja, se a string não contém
	 * somente dígitos unicode.
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageNullPointer</code> (segundo parâmetro String), caso o valor
	 * passado seja <code>null</code>
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageIllegalArgument</code> (terceiro parâmetro String), caso o
	 * valor passado não seja numérico.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param messageNullPointer
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @param messageIllegalArgument
	 *            - Mensagem utiliada na exceção.
	 * 
	 * 
	 * @thows IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string não seja numérica.
	 * 
	 * @since 0.2
	 */
	private static void checkNotNumeric(String value,
			String messageNullPointer, String messageIllegalArgument) {

		Objects.checkNotNull(value, messageNullPointer);

		if (!isNumeric(value)) {
			throw new IllegalArgumentException(messageIllegalArgument);
		}
	}

	/**
	 * <p>
	 * Método privado para fins de reutilização de código.
	 * </p>
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code>, não é vazia (<code>StringUtils.EMPTY</code>) e não
	 * possui apenas espaços em branco.
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageNullPointer</code> (segundo parâmetro String), caso o valor
	 * passado seja <code>null</code>
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageIllegalArgument</code> (terceiro parâmetro String), caso o
	 * valor passado seja vazio.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param messageNullPointer
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @param messageIllegalArgument
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @thows IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string seja vazia.
	 * 
	 * @since 0.2
	 */
	private static void checkNotBlank(String value, String messageNullPointer,
			String messageIllegalArgument) {

		Objects.checkNotNull(value, messageNullPointer);

		if (isBlank(value)) {
			throw new IllegalArgumentException(messageIllegalArgument);
		}
	}
}
